import { ElementNode, element } from './element';
import { LayoutParamAttributes } from './layoutparams';
import { MainTypes } from './main';
// types
/* generated @ 2018-11-13T12:44:09.620 */
export namespace CoordinatorlayoutTypes {
  export interface CoordinatorLayoutAttributes extends MainTypes.ViewGroupAttributes {
  }
  export interface CoordinatorLayoutLayoutParamsAttributes extends MainTypes.ViewGroupMarginLayoutParamsAttributes {
    android_layout_gravity?: number;
    layout_anchor?: string;
    layout_anchorGravity?: number | LayoutAnchorGravityFlagsEnum[];
    layout_behavior?: string;
    layout_dodgeInsetEdges?: LayoutDodgeInsetEdgesFlagsEnum[];
    layout_insetEdge?: LayoutInsetEdgeEnum;
    layout_keyline?: number;
  }
  export enum LayoutAnchorGravityFlagsEnum {
    bottom = 'bottom',
    center = 'center',
    center_horizontal = 'center_horizontal',
    center_vertical = 'center_vertical',
    clip_horizontal = 'clip_horizontal',
    clip_vertical = 'clip_vertical',
    end = 'end',
    fill = 'fill',
    fill_horizontal = 'fill_horizontal',
    fill_vertical = 'fill_vertical',
    left = 'left',
    right = 'right',
    start = 'start',
    top = 'top'
  }
  export enum LayoutDodgeInsetEdgesFlagsEnum {
    all = 'all',
    bottom = 'bottom',
    end = 'end',
    left = 'left',
    none = 'none',
    right = 'right',
    start = 'start',
    top = 'top'
  }
  export enum LayoutInsetEdgeEnum {
    bottom = 'bottom',
    end = 'end',
    left = 'left',
    none = 'none',
    right = 'right',
    start = 'start',
    top = 'top'
  }
}
// elements
export const CoordinatorLayout = (
  attributes?: CoordinatorlayoutTypes.CoordinatorLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<CoordinatorlayoutTypes.CoordinatorLayoutAttributes, LayoutParamAttributes> => {
  return element('coordinatorLayout', attributes, children);
};