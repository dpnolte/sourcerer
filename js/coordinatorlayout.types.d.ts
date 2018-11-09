/* generated @ 2018-11-09T15:28:29.820 */
declare namespace CoordinatorlayoutTypes {
  interface CoordinatorLayoutAttributes extends MainTypes.ViewGroupAttributes {
  }

  interface CoordinatorLayoutLayoutParamsAttributes extends MainTypes.ViewGroupMarginLayoutParamsAttributes {
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
