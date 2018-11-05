/* generated @ 2018-11-05T13:35:58.530 */
import "./generated.attributes";

declare module "LayoutTypes" {
  interface CoordinatorLayoutAttributes extends ViewGroupAttributes {
  }

  interface CoordinatorLayoutLayoutParamsAttributes extends ViewGroupMarginLayoutParamsAttributes {
    android_layout_gravity?: number;
    layout_anchor?: string;
    layout_anchorGravity?: any;
    layout_behavior?: string;
    layout_dodgeInsetEdges?: string;
    layout_insetEdge?: LayoutInsetEdgeEnum;
    layout_keyline?: number;
  }

  enum LayoutInsetEdgeEnum { 'none', 'top', 'bottom', 'left', 'right', 'start', 'end' }
}
